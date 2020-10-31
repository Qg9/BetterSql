package fr.better.sql.help;

import fr.better.sql.exception.BetterSqlException;

import java.util.List;

public class ResultBuilder implements Result{

    private List<List<Object>> complexContents;
    private List<Object> content;

    public ResultBuilder(){}

    @Override
    public List<Object> get() throws BetterSqlException {

        if(content == null){
            throw new NullPointerException("This request don't have any result.");
        }

        if(content.isEmpty()){
            throw new BetterSqlException("The result of your request is empty ?");
        }

        return content;
    }

    public void setComplexContents(List<List<Object>> complexContents) {
        this.complexContents = complexContents;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

    @Override
    public List<List<Object>> nextResult() {
        return complexContents;
    }
}
