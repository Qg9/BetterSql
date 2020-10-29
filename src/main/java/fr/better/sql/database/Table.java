package fr.better.sql.database;

import fr.better.sql.request.AddRequest;
import fr.better.sql.request.DeleteRequest;
import fr.better.sql.request.SelectRequest;
import fr.better.sql.request.UpdateRequest;

public interface Table {

    public AddRequest add(Object... contents);
    public UpdateRequest update(Object... contents);

    public DeleteRequest delete();
    public SelectRequest select();
}
