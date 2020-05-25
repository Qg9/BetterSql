# BetterSql Wiki
 
##Get Started

##Create a Database 
To start, we will have to create Database. The library uses SQLite, so the db will be local. 
Just make a Database object ``new Database(String where, String name);``, and use the function ``connect();``
```Java
 Database db = new Database("C:/Users/gq179/Desktop", "storage");
 db.connect();
```

##Create Your Table
