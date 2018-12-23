/*
 * DB4O manager class
 */
package core.persistence;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.IncompatibleFileFormatException;
import com.db4o.ext.OldFormatException;
import com.db4o.query.Predicate;
import core.ObjectCounter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import core.ProgramException;
import core.commons.CommonObject;

/**
 *
 * @author Ricard P. Barnes
 */
public class Persistence {

    private static final String DB_PATH = "data" + File.separator + "data.db4o";

    private static ObjectContainer db;
    private static Persistent object;

    private static void connect() throws ProgramException {
        try {
            EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
            //config.file().generateUUIDs(ConfigScope.GLOBALLY);
            //config.common().objectClass(object.getClass()).cascadeOnDelete(false);
            config.common().objectClass(object.getClass()).cascadeOnUpdate(true);
            //config.common().objectClass(X.class).cascadeOnActivate(true);
            //config.common().updateDepth(5);
            //config.common().activationDepth(5);
            db = Db4oEmbedded.openFile(config, DB_PATH);
        } catch (Db4oIOException | DatabaseFileLockedException | IncompatibleFileFormatException | OldFormatException | DatabaseReadOnlyException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }
    }

    private static void disconnect() {
        db.close();
        object = null;
    }

    public static void save(Persistent aThis) throws ProgramException {
        object = aThis;
        ObjectSet<Persistent> resultats;
        connect();
        resultats = db.query(new Predicate<Persistent>() {
            @Override
            public boolean match(Persistent o) {
                return o.getClass() == object.getClass() && o.getId().equals(object.getId());
            }
        });
        if (resultats.hasNext()) {
            db.delete(resultats.next());
            db.commit();
        }
        db.store(object);
        db.commit();
        disconnect();
    }

    public static Persistent load(Persistent aThis) throws ProgramException {
        object = aThis;
        ObjectSet<Persistent> results;
        Persistent loadedObject;
        connect();
        results = db.query(new Predicate<Persistent>() {
            @Override
            public boolean match(Persistent o) {
                return o.getClass() == object.getClass() && o.getId().equals(object.getId());
            }
        });
        if (results.hasNext()) {
            loadedObject = results.next();
            disconnect();
            return loadedObject;
        } else {
            disconnect();
            throw new ProgramException(ProgramException.NOT_FOUND);
        }
    }

    public static void delete(CommonObject aThis) throws ProgramException {
        object = aThis;
        ObjectSet<Persistent> results;
        connect();
        results = db.query(new Predicate<Persistent>() {
            @Override
            public boolean match(Persistent o) {
                return o.getClass() == object.getClass() && o.getId().equals(object.getId());
            }
        });
        if (results.isEmpty()) {
            disconnect();
            throw new ProgramException(ProgramException.NOT_FOUND);
        } else {
            db.delete(results.next());
            db.commit();
            disconnect();
        }
    }

    public static List<Persistent> getAll(Persistent pObject) throws ProgramException {
        object = pObject;
        connect();
        ObjectSet<Persistent> results;
        List<Persistent> objectList;
        results = db.query(new Predicate<Persistent>() {
            @Override
            public boolean match(Persistent o) {
                return o.getClass() == object.getClass();
            }
        });
        if (!results.isEmpty()) {
            objectList = new ArrayList<>();
            while (results.hasNext()) {
                objectList.add(results.next());
            }
        } else {
            disconnect();
            throw new ProgramException(ProgramException.NOTHING_FOUND);
        }
        disconnect();
        return objectList;
    }

    public static void saveCounter(Persistent counter) throws ProgramException {
        disconnect();
        object = counter;
        ObjectSet<ObjectCounter> results;
        connect();
        results = db.query(new Predicate<ObjectCounter>() {
            @Override
            public boolean match(ObjectCounter o) {
                return o.getClass() == counter.getClass() && ((ObjectCounter) o).getType() == ((ObjectCounter) counter).getType();
            }
        });
        if (!results.isEmpty()) {
            db.delete(results.next());
        }
        db.store(object);
        disconnect();
    }

    public static ObjectCounter loadCounter(Persistent counter) throws ProgramException {
        object = counter;
        ObjectSet<ObjectCounter> results;
        connect();
        results = db.query(new Predicate<ObjectCounter>() {
            @Override
            public boolean match(ObjectCounter o) {
                return o.getClass() == counter.getClass() && ((ObjectCounter) o).getType() == ((ObjectCounter) counter).getType();
            }
        });
        if (!results.isEmpty()) {
            return results.next();
        } else {
            saveCounter(counter);
            return (ObjectCounter) counter;
        }
    }

}
