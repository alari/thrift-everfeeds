@Typed package everfeeds.remote.evernote

import everfeeds.mongo.AccessD
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.THttpClient
import com.evernote.edam.notestore.NoteStore
import com.evernote.edam.userstore.UserStore
import everfeeds.ConfigHolder
import everfeeds.remote.Raw

/**
 * @author Dmitry Kurinskiy
 * @since 31.05.11 20:36
 */
class EvernoteRaw extends Raw{
  static private EvernoteRaw instance = new EvernoteRaw()

  static public EvernoteRaw getInstance(){
    instance
  }

  private EvernoteRaw(){}

  private ConfigObject getRemoteConfig(){
    (ConfigHolder.config.get("remote") as ConfigObject).get("evernote") as ConfigObject
  }

  public NoteStore.Client getNoteStore(AccessD access){
    if(testingContent) {
      return (NoteStore.Client)cleanTestingContent
    }

    THttpClient noteStoreTrans = new THttpClient( remoteConfig.get("noteStoreUrl").toString() + access.params.shard );
    TBinaryProtocol noteStoreProt = new TBinaryProtocol(noteStoreTrans);
    new NoteStore.Client(noteStoreProt, noteStoreProt);
  }

  public UserStore.Client getUserStore(){
    THttpClient userStoreTrans = new THttpClient(remoteConfig.get("userStoreUrl").toString());
    userStoreTrans.setCustomHeader("User-Agent", remoteConfig.get("userAgent").toString());
    TBinaryProtocol userStoreProt = new TBinaryProtocol(userStoreTrans);
    new UserStore.Client(userStoreProt, userStoreProt);
  }
}
