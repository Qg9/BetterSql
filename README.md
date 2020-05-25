# BetterSql Wiki
 
## Get Started

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
