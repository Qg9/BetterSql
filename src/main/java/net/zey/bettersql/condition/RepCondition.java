package net.zey.bettersql.condition;

public class RepCondition extends Condition{

    /*

        This library was made by Zey,
        The objective was to create a new Save System, more easy and swift :d
        You have no right to take back, copy or steal the code of this class or the entire library.
        You have more information on how to use the library in readme.md
        Thanks, Zey.

     */

    private final Object object;

    public RepCondition(String adding, Object object) {
        super(adding);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
