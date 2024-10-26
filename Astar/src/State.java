import java.util.Objects;

class State {
    int b1, b2, b3;

    public State(int b1, int b2, int b3) {
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return b1 == state.b1 && b2 == state.b2 && b3 == state.b3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(b1, b2, b3);
    }

    @Override
    public String toString() {
        return "(" + b1 + ", " + b2 + ", " + b3 + ")";
    }
}
