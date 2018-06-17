package com.example.weiyupeng.hybridradio;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.weiyupeng.hybridradio.StationInformationModule.Bearer;
import com.example.weiyupeng.hybridradio.StationInformationModule.Genre;
import com.example.weiyupeng.hybridradio.StationInformationModule.MediaDescription;
import com.example.weiyupeng.hybridradio.StationInformationModule.Multimedia;
import com.example.weiyupeng.hybridradio.StationInformationModule.ServiceGroup;
import com.example.weiyupeng.hybridradio.StationInformationModule.ServiceInformation;
import com.example.weiyupeng.hybridradio.db.DBGenre;
import com.example.weiyupeng.hybridradio.db.DBService;
import com.example.weiyupeng.hybridradio.db.DBServiceGroup;
import com.thoughtworks.xstream.XStream;

import org.radiodns.Application;
import org.radiodns.LookupException;
import org.radiodns.RadioDNS;
import org.radiodns.Record;
import org.radiodns.Service;

import java.io.IOException;
import java.util.HashMap;

import io.realm.Realm;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class LookupIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_START = "com.example.weiyupeng.hybridradio.action.StartLookup";
    private static final String ACTION_STOP = "com.example.weiyupeng.hybridradio.action.StopLookup";

    // TODO: Rename parameters
    private static final String GCC = "com.example.weiyupeng.hybridradio.extra.GCC";
    private static final String PICODE = "com.example.weiyupeng.hybridradio.extra.PICODE";
    private static final String FREQUENCY = "com.example.weiyupeng.hybridradio.extra.FREQUENCY";

    public static final String TAG = "LookupIntentService";
    public static final String LOOKUP_SERVICE_MESSAGE = "Lookup Service Message";
    public static final String LOOKUP_UPDATE = "Lookup update";

    private ServiceInformation serviceInformation = null;
    private RadioDNS rdns = new RadioDNS("8.8.8.8");
    private String url = null;
    public enum Status{
        Linking,
        connecting,
        pulling,
        parsing,
        saving,
        finished,
        error
    }

    public LookupIntentService() {
        super("LookupIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStart(Context context, String param1, String param2, String param3) {
        Intent intent = new Intent(context, LookupIntentService.class);
        intent.setAction(ACTION_START);
        intent.putExtra(GCC, param1);
        intent.putExtra(PICODE, param2);
        intent.putExtra(FREQUENCY, param3);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStop(Context context) {
        Intent intent = new Intent(context, LookupIntentService.class);
        intent.setAction(ACTION_STOP);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                final String param1 = intent.getStringExtra(GCC);
                final String param2 = intent.getStringExtra(PICODE);
                final String param3 = intent.getStringExtra(FREQUENCY);
                handleActionStart(param1, param2, param3);
            } else if (ACTION_STOP.equals(action)) {
                handleActionStop();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStart(String gcc, String piCode,String frequency) {
        // Internet permission is required
        Log.d(TAG,"handleActionStart +" );
        Log.d(TAG, "GCC = " + gcc + " PI Code = " + piCode + " Frequency = " + frequency);
        updateStatus(Status.Linking);

        this.url = FMlookup(gcc,piCode,frequency);
        if(url != null){
            updateStatus(Status.connecting);

        }
        else{
            updateStatus(Status.error);
            return;
        }
        if(SPIHttpRequest(gcc,piCode,frequency) && serviceInformation != null )
        {
            updateStatus(Status.parsing);
            if(parseAndInsertData())
            {
              updateStatus(Status.finished);
            }
            else
            {
                updateStatus(Status.error);
            }
        }else{
            updateStatus(Status.error);
            return;
        }





    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStop() {
        Log.d(TAG,"handleActionStop +" );
    }

    private void updateStatus(Status status){
            Intent intent = new Intent(LOOKUP_SERVICE_MESSAGE); //action name
            switch (status)
            {
                case error:
                    intent.putExtra(LOOKUP_UPDATE,"OOOOOps something wrong, try it later");
                    break;
                case Linking:
                    intent.putExtra(LOOKUP_UPDATE,"trying to link to service ...");
                    break;
                case connecting:
                    intent.putExtra(LOOKUP_UPDATE,"trying to connect to service ...");
                    break;
                case pulling:
                    intent.putExtra(LOOKUP_UPDATE,"trying to pull the data from service ...");
                    break;
                case parsing:
                    intent.putExtra(LOOKUP_UPDATE,"trying to parse the data ...");
                    break;
                case saving:
                    intent.putExtra(LOOKUP_UPDATE,"trying to save the data locally");
                    break;
                case finished:
                    intent.putExtra(LOOKUP_UPDATE,"finished !!!");
                    break;
                    default:
                        Log.e(TAG, "ERROR, NO STATUS MATCHING !!!!");
            }



        //broadcast this information
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }


    /*
    lifecycle is totally depend on the the task itself, not attached with activity or anything
     */


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"On Create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"On Destroy");
    }


    /************ Inner Logic Method **********/
    private String FMlookup(String gcc, String piCode,String frequency){
        String target = null;
        int port = -1;
        Service service = null;
        Application application = null;

        try {
            service = rdns.lookupFMService("ce1", "c479", 95800);
            Log.d(TAG, "GCC = " + gcc + " PI Code = " + piCode + " Frequency = " + Integer.parseInt(frequency));

            if(service != null){
                application = service.getApplication(RadioDNS.RADIOEPG);
                Log.d(TAG, service.getApplications().toString());
            }else{
                Log.e(TAG, "service is null");
            }
            if (application != null){

                for (Record record : application.getRecords()){
                    target = record.getTarget().toString();
                    port = record.getPort();
                    url = "http://" + target.substring(0,target.length()-1) + ":" +record.getPort();
                    Log.d(TAG, "lookup result: " + url);

                }
            }else{
                Log.e(TAG,"application is null");
            }
        } catch (LookupException e) {
            e.printStackTrace();
        }
        return url;
    }

    private boolean parseAndInsertData(){

        Realm myRealm = Realm.getInstance(getApplicationContext());
        myRealm.beginTransaction();
        boolean status = true;
        HashMap<String,DBServiceGroup> serviceGroupHashMap = new HashMap<String,DBServiceGroup>();

        // parse service group
        for(ServiceGroup mServiceGroup : serviceInformation.getServiceGroups()){
            if(myRealm.where(DBServiceGroup.class).equalTo("id",mServiceGroup.getId()).count()!= 0)
            {
                Log.i(TAG,"service group is added before : " +mServiceGroup.getId());
                continue;
            }
            DBServiceGroup dbServiceGroup = myRealm.createObject(DBServiceGroup.class);

            dbServiceGroup.setId(mServiceGroup.getId());
            serviceGroupHashMap.put(mServiceGroup.getId(),dbServiceGroup);

            dbServiceGroup.setShortName(mServiceGroup.getShortName());
            dbServiceGroup.setMediumName(mServiceGroup.getMediumName());
            dbServiceGroup.setLongName(mServiceGroup.getLongName());
            int picSize = -1;
            for(MediaDescription mediaDescription : mServiceGroup.getMediaDescriptionList())
            {
                Multimedia multimedia = mediaDescription.getMultimedia();
                if (  multimedia!= null)
                {
                    if(multimedia.getWidth() != null && picSize < Integer.parseInt(multimedia.getWidth()))
                    {
                        picSize = Integer.parseInt(multimedia.getWidth());
                        dbServiceGroup.setMaxPicURL(multimedia.getUrl());
                    }
                }
                else if(mediaDescription.getLongDescription() != null)
                {
                    dbServiceGroup.setDescription(mediaDescription.getLongDescription());
                }
                else if(mediaDescription.getMediumDescription() != null)
                {
                    dbServiceGroup.setDescription(mediaDescription.getMediumDescription());
                }
                else if(mediaDescription.getShortDescription() != null)
                {
                    dbServiceGroup.setDescription(mediaDescription.getShortDescription());
                }

            }

        }

        //parse Service

        for(com.example.weiyupeng.hybridradio.StationInformationModule.Service service : serviceInformation.getServices().getServiceList())
        {
            if(service.getServiceGroupMember() != null && service.getServiceGroupMember().getId()!= null)
            {
                if(!serviceGroupHashMap.containsKey(service.getServiceGroupMember().getId()))
                {
                    Log.e(TAG,"Service is existed in service group:" + service.getServiceGroupMember().getId());
                    continue;
                }
            }
            else
            {
                Log.e(TAG,"Error !!! no group id:" + service.getLongName());
                continue;
            }



            DBService dbService = myRealm.createObject(DBService.class);
            dbService.setShortName(service.getShortName());
            dbService.setMediumName(service.getMediumName());
            dbService.setLongName(service.getLongName());
            int picSize = -1;

            if(service.getLongDescription() != null)
            {
                dbService.setDescription(service.getLongDescription());
            }
            else if(service.getMediumDescription() != null)
            {
                dbService.setDescription(service.getMediumDescription());
            }
            else if(service.getShortDescription() != null)
            {
                dbService.setDescription(service.getShortDescription());
            }

            if(service.getGenreList() != null)
            {
                for(Genre genre : service.getGenreList())
                {
                    DBGenre dbGenre = null;
                    if(myRealm.where(DBGenre.class).equalTo("genre",genre.getType()).count() != 0)
                    {
                        dbGenre = myRealm.where(DBGenre.class).equalTo("genre",genre.getType()).findFirst();
                    }
                    else
                    {
                        dbGenre = myRealm.createObject(DBGenre.class);
                        dbGenre.setGenre(genre.getType());
                    }
                    if(dbGenre != null) dbService.getGenreList().add(dbGenre);
                }
            }


            for(MediaDescription mediaDescription : service.getMediaDescriptionList())
            {
                Multimedia multimedia = mediaDescription.getMultimedia();
                if (  multimedia!= null)
                {
                    if(multimedia.getWidth() != null && picSize < Integer.parseInt(multimedia.getWidth())) {
                        picSize = Integer.parseInt(multimedia.getWidth());
                        dbService.setMaxPicURL(multimedia.getUrl());
                    }
                }
                else if(mediaDescription.getLongDescription() != null)
                {
                    dbService.setDescription(mediaDescription.getLongDescription());
                }
                else if(mediaDescription.getMediumDescription() != null)
                {
                    dbService.setDescription(mediaDescription.getMediumDescription());
                }
                else if(mediaDescription.getShortDescription() != null)
                {
                    dbService.setDescription(mediaDescription.getShortDescription());
                }
            }
            for(Bearer bearer : service.getBearerList())
            {
                if(bearer.getBitrate() != null){
                    dbService.setAudioSourceURL(bearer.getId());
                }
            }

            dbService.setGroupID(service.getServiceGroupMember().getId());

            if(serviceGroupHashMap.get(dbService.getGroupID()) != null)
            {
                serviceGroupHashMap.get(dbService.getGroupID()).getServices().add(dbService);
            }
        }

        updateStatus(Status.saving);
        // store in DB
        myRealm.commitTransaction();
        myRealm.close();
        return status;
    }

    private boolean SPIHttpRequest(String gcc, String piCode,String frequency){
        String mSPIString =  url + "/radiodns/spi/3.1/SI.xml";
        Log.d(TAG, "mSPIString = " + mSPIString );

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(mSPIString).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String xmlString = response.body().string();
            //Log.d(TAG,xmlString);
            updateStatus(Status.parsing);
            XStream xstream = new XStream();
            xstream.processAnnotations(ServiceInformation.class);
            if(xmlString!=null) {
                System.out.println("change XML to Object");
                serviceInformation = (ServiceInformation) xstream.fromXML(xmlString);
                Log.d(TAG,"serviceInformation =" + serviceInformation.getServices().getServiceList().get(1).getLongName());
            }

        } catch (IOException e) {
            updateStatus(Status.error);
            e.printStackTrace();
        }
        return true;
    }


    /************ Inner Logic Method  End**********/
}
