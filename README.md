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

<dependencies>
  <dependency>
    <groupId>com.github.Zey-dev</groupId>
    <artifactId>BetterSql</artifactId>
    <version>1.2</version>
  </dependency>
</dependencies>
```
### Gradle

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.Zey-dev:BetterSql:1.2'
}
```
[![](https://jitpack.io/v/Zey-dev/BetterSql.svg)](https://jitpack.io/#Zey-dev/BetterSql)

# How To Use

## Create a Database 
To start, we will have to create Database. The library uses SQLite, so the db will be local. 
Just make a Database object ``new Database(String where, String name);``, and use the function ``Database#connect();``
```Java
 Database db = new SqliteDatabase("C:/Users/gq179/Desktop", "storage");
 Database db = new HikariDatabase("storage", "Zey", "MyPassWord", "https://github.com/database", 3306, 20);
 /*Version before 1.1*/ db.connect();
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

It's a bit long, isn't it? That's why I created the class H. It allows you to make shortcuts like the function ``H#args(String type, String name, int value, String default);`` Don't use the enumerations, but only the first 2 letters, for more explanation, just look directly in the .java of the enum. In 0.0.6, you have new function, ``H.varArgs(String name, int value);`` (They are : ``varArgs``, ``intArgs`` and ``textArgs``. They return an ArgumentsType like the function name and a NO_NULL default value.

```Java
//That was the same result

/*Before 1.1*/
Table t = db.getTable("city", Arrays.asList(H.args("va", "name", 32, H.args("in", "age", 1024, "no"), H.args("te", "bio", 1024, "no"));
/*After*/
Table t = db.getTable("city", H.args("va", "name", 32, H.args("in", "age", 1024, "no"), H.args("te", "bio", 1024, "no");
t.createTable();
```
We can simplify more !!! I've added more speedy function !
```Java
Table t = db.getTable("city", H.varArgs("name", 32), H.intArgs("age", 4), H.textArgs("bio", 1024));
t.createTable();
```
## Request

### Add Request

To insert a line in a database you just have to use the function ``Table#add(SQLObject...)``, SQLObject can either be a String; or an int, or a date. You can create it with the constructor or with my helper class (H) with the function ``H#ob(String/int/Date);``.
```java
t.add(H.ob("Adrien"), H.ob(14), H.ob("A very good baker!")).sendSql();
```

### Update Request

To update a database, use the function ``Table#update(HashMap<String, SQLObject>)`` or ``Table#updateAll(SQLObject...);``, and ``Request#sendSql()`` afterwards. To make it faster, you can always use my H class which has a function to create HashMap, ``H#hash(List<String>, List<SQLObject>);`` You can add the function ``Request#where(String, SQLObject);`` which adds a condition, this condition is mandatory for certain request like select or remove.
```java
t.update(H.hash(Arrays.asList("age", "bio"), Arrays.asList(H.ob(15), H.ob("My nex biographie")).where("name", H.ob("Zey")).sendSql();
t.updateAll(H.ob("Zey"), H.ob(15), H.ob("My new Biographie")).where("name", H.ob("Zey")).sendSql();
```

### Select Request

To Select a row in a database, you must use the ``Table#select()`` function; with a condition. The ``SelectRequest#sendSql();`` return a List<SQLObject>. If the list is empty, the condition return false, and therefore the line you are looking for does not exist. You can therefore make a ``Table#add();``. Finally use ``.get()``.
```java
List<SQLObject> all = t.select().where("name", H.ob("Zey", Sym.EQU)).sendSql().get();

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
t.delete().where("name", H.ob("Zey"), Sym.EQU).sendSql();
```

## Thanks !

Thanks for using my library, if you want to give me a donations don't wait x) 

#### » [My Paypal](https://www.paypal.me/zeydev)
#### » [My Discord](https://discord.gg/BDbexTa)
#### » [My Website](https://zey-dev.fr)
