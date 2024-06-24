package pl.gromotj.exclusionzone.functions;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

public class CustomUUIDGenerator implements IdentifierGenerator, Configurable {
    private String prefix;

    @Override
    public void configure(org.hibernate.type.Type type, Properties params, ServiceRegistry serviceRegistry) {
        this.prefix = params.getProperty("prefix", "null");
        if (this.prefix.length() != 4) {
            throw new HibernateException("Prefix must have 4 characters");
        }
    }

    @Override
    public String generate(SharedSessionContractImplementor session, Object object) {
        String uuid = UUID.randomUUID().toString();
        return prefix+"-"+ uuid;
    }
}
