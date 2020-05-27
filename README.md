# BetterSql Wiki
 
## Get Started

### Java Version 

| Version | Supported          |Recommended       | 
| ------- | ------------------ |------------------|
| > 8.0   | :white_check_mark: |:x:               |
| 8.0.x   | :white_check_mark: |:white_check_mark:|
| < 8.0   | :x:                |:x:               |

### Maven

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.Zey-dev</groupId>
  <artifactId>BetterSql</artifactId>
  <version>0.0.3</version>
</dependency>
```
### Gradle

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.Zey-dev:BetterSql:0.0.3'
}
```
## Create a Database 
To start, we will have to create Database. The library uses SQLite, so the db will be local. 
Just make a Database object ``new Database(String where, String name);``, and use the function ``Database#connect();``
```Java
 Database db = new Database("C:/Users/gq179/Desktop", "storage");
 db.connect();
```

## Create Your Table
To create your table, simply create a table object with the function ``Database#getTable(String name, List <TableArguments> args); ``. The `` TableArguments`` object is composed of a type, a name, a maximum value, and finally a default type. If you want to know the usefulness of each of the enumerations, these are specified in the .java. After that, just make a `` Table#createTable (); ``. If the default type is DEFAULT_VALUE, you must add ``TableArguments#setData(String data);``
```Java
List<TableArguments> tbArgs = new ArrayList<>();
tbArgs.add(new TableArguments(TableArgumentsType.VARCHAR, "name", 32, TableDefaultArgumentsType.NO));
tbArgs.add(new TableArguments(TableArgumentsType.INT, "age", 1024, TableDefaultArgumentsType.NO));
tbArgs.add(new TableArguments(TableArgumentsType.VARCHAR, "bio", 32, TableDefaultArgumentsType.DEFAULT_VALUE).setData("Default Biographie Set !"));

Table t = db.getTable("city", tbArgs);
t.createTable();
```

It's a bit long, isn't it? That's why I created the class H. It allows you to make shortcuts like the function ``H#args(String type, String name, int value, String default);`` Don't use the enumerations, but only the first 2 letters, for more explanation, just look directly in the .java of the enum.

```Java
//That was the same result
Table t = db.getTable("city", Arrays.asList(H.args("va", "name", 32, H.args("in", "age", 1024, "no"), H.args("te", "bio", 1024, "no"));
t.createTable();
```

## Request

### Add Request

To insert a line in a database you just have to use the function ``Table#add(List<SQLObject>)``, SQLObject can either be a String; or an int, or a date. You can create it with the constructor or with my helper class (H) with the function ``H#ob(String/int/Date);``.
```java
t.add(Arrays.asList(H.ob("Adrien"), H.ob(14), H.ob("A very good baker!"))).sendSql();
```

### Update Request

To update a database, use the function Table#update(HashMap<String, SQLObject>), and Request#sendSql()`` afterwards. To make it faster, you can always use my H class which has a function to create HashMap, ``H#hash(List<String>, List<SQLObject>);`` You can add the function ``Request#where(String, SQLObject);`` which adds a condition, this condition is mandatory for certain request like select or remove.
```java
t.update(H.hash(Arrays.asList("age", "bio"), Arrays.asList(H.ob(15), H.ob("My nex biographie")).where("name", H.ob("Zey")).sendSql();
```

### Select Request

To Select a row in a database, you must use the ``Table#select()`` function; with a condition. The ``SelectRequest#sendSql();`` return a List<SQLObject>. If the list is empty, the condition return false, and therefore the line you are looking for does not exist. You can therefore make a ``Table#add();``
```java
List<SQLObject> all = t.select().where("name", H.ob("Zey")).sendSql();

        String bio;
        int age;

        if(all.isEmpty()){
            //Do when player isn't in the database
            //Exemple :
            t.add(Arrays.asList(H.ob("Zey"), H.ob(14), H.ob("A default Biographie"))).sendSql();
            age = 14;
            bio = "A default Biographie";
        }else{
            //If the player was in the database
            age = all.get(1).getInt();
            bio = all.get(2).getString();
        }
```
### Delete Request 

To delete a Row in a database, you must use ``Table#delete()`` function and a condition. Don't forget to use ``RemoveRequest#sendSql()`` 
to send your request.

```java
t.delete().where("name", H.ob("Zey")).sendSql();
```

## Thanks !

Thanks for using my library, if you want to give me a donations don't wait x) 

#### » [My Paypal](https://www.paypal.me/zeydev)
#### » [My Discord](https://discord.gg/BDbexTa)
#### » [My Website](https://zey-dev.fr)
