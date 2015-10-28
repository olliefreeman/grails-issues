# Grails 3 Issues

This project is a repository for code samples reproducing issues found in Grails 3

The following extra config is set in application.yml
```yml
grails.converters.xml.default.deep: true
gorm:
    failOnError: true
    autoFlush: true
```

Currently there are the following issues:

# Rollback Issue [Grails 3.0.? - 3.0.8]

[Grails 3.0.8 integration test rollback 'No Session found for current thread'](https://github.com/grails/grails-core/issues/9304)

`Rollback` class is annotated with `@Resource` to make it available to a Restful interface

## To reproduce

This is reproduced using `Rollback` domain class and the `RollbackControllerSpec` test.

```bash
$ grails
grails> test-app rollback.issue.RollbackControllerSpec -integration
```

## The issue

Currently `@Rollback` annotation does not allow access to the JPA/Hibernate session which means even inside the test there is no way to save data to the database for extraction testing purposes.
The only solution at the moment is to have the data bootstrapped in at the start of the test using the `Bootstrap` class. However this prevents testing against an empty database.

# HasMany/BelongsTo Issue from databinding **FIXED in grails 3.0.9**

[Grails 3.0.8 hasMany/belongsTo validation and save issue](https://github.com/grails/grails-core/issues/9308)

`Has` class is annotated with `@Resource` to make it available to a Restful interface

## To reproduce

Using

* Domains
  * belongs.to.Belong
  * belongs.to.Has
* Test
  * HasControllerSpec

```bash
$ grails
grails> test-app belongs.to.issue.HasControllerSpec -integration
```

## The issue

This is actually two-fold

### Validation is not cascading
The hasMany elements are not being validated which means save fails with exception as the belongs elements are invalid

### Belongs elements invalid
Because they are not having the owner set when they are data bound, without the owner set but inside the owner databinding method.
