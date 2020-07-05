package com.ghriit.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.ghriit.model.Batch;
import com.ghriit.model.Course;
import com.ghriit.model.Faculty;
import com.ghriit.model.Student;

public class HibernateUtil {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {

			Map<String, Object> map =new HashMap<>();
			map.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			map.put(Environment.URL, "jdbc:mysql://localhost:3306/cmshibernate");
			map.put(Environment.USER, "root");
			map.put(Environment.PASS, "root");
			map.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			map.put(Environment.HBM2DDL_AUTO, "update");
			map.put(Environment.SHOW_SQL, "true");
			map.put(Environment.USE_SECOND_LEVEL_CACHE,"true");
			map.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.EhCacheRegionFactory");

			registry = new StandardServiceRegistryBuilder().applySettings(map).build();
			MetadataSources mds = new MetadataSources(registry);

			mds.addAnnotatedClass(Batch.class)
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(Faculty.class)
			.addAnnotatedClass(Student.class);
			Metadata md =mds.getMetadataBuilder().build();
			sessionFactory =md.getSessionFactoryBuilder().build();

		}
		return sessionFactory;
	}
}
