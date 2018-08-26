package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ {

	public static volatile SingularAttribute<Author, String> gender;
	public static volatile SingularAttribute<Author, String> name;
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, Date> birthDate;
	public static volatile SingularAttribute<Author, String> email;

}

