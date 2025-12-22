package via.pro3.station_server_2.Utils;

import java.io.IOException;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * A Gson TypeAdapter that unwraps Hibernate proxies before serialization.
 * This prevents "Attempted to serialize java.lang.Class" errors.
 */
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    private final Gson context;

    private HibernateProxyTypeAdapter(Gson context) {
        this.context = context;
    }

    @Override
    public void write(JsonWriter out, HibernateProxy value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        // Initialize the proxy to ensure data is loaded
        Object unproxiedValue = Hibernate.unproxy(value);

        // Get the type of the real object
        TypeAdapter delegate = context.getAdapter(unproxiedValue.getClass());

        // Delegate serialization to the real object's adapter
        delegate.write(out, unproxiedValue);
    }

    @Override
    public HibernateProxy read(JsonReader in) throws IOException {
        throw new UnsupportedOperationException("Not supported");
    }

    // Factory to register this adapter with GsonBuilder
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType())
                    ? (TypeAdapter<T>) new HibernateProxyTypeAdapter(gson)
                    : null);
        }
    };
}
