package lsp;

public abstract class Bird {
    public abstract void eat();

    public void fly() {
        throw new UnsupportedOperationException("Эта птица не умеет летать");
    }
}