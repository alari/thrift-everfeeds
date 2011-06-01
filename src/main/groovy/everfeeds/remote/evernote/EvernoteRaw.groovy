package everfeeds.remote.evernote

import everfeeds.mongo.AccessD
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.THttpClient
import com.evernote.edam.notestore.NoteStore
import com.evernote.edam.userstore.UserStore

/**
 * @author Dmitry Kurinskiy
 * @since 31.05.11 20:36
 */
class EvernoteRaw {
  static private EvernoteRaw instance = new EvernoteRaw()

  static public EvernoteRaw getInstance(){
    instance
  }

  private EvernoteRaw(){}

  private NoteStore.Client getNoteStore(AccessD access){
    THttpClient noteStoreTrans = new THttpClient(noteStoreUrl);
    TBinaryProtocol noteStoreProt = new TBinaryProtocol(noteStoreTrans);
    new NoteStore.Client(noteStoreProt, noteStoreProt);
  }

  private UserStore.Client getUserStore(AccessD access){
    THttpClient userStoreTrans = new THttpClient(userStoreUrl);
    userStoreTrans.setCustomHeader("User-Agent", config.userAgent);
    TBinaryProtocol userStoreProt = new TBinaryProtocol(userStoreTrans);
    new UserStore.Client(userStoreProt, userStoreProt);
  }
}
