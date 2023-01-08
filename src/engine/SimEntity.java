package engine;

public abstract class SimEntity {
    final private SimuEngine engine;
    public SimEntity(SimuEngine eng){
        this.engine = eng;
        this.engine.addEntity(this);
    }

    public abstract void init();

    public SimuEngine getEngine() {
        return this.engine;
    }
}
