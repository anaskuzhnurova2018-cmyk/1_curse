public class Complex {
    double r; //действительная часть
    double i; //мнимая часть

    public Complex(double r, double i) { //точка на плоскости
        this.r = r;
        this.i = i;
    }

    public Complex add(Complex other) { //(a+bi)+(c+di) = (a+c) + (b+d)i
        return new Complex(this.r + other.r, this.i + other.i);
    } //сложение

    public Complex sub(Complex other) { //(a+bi)-(c+di) = (a-c) + (b-d)i
        return new Complex(this.r - other.r, this.i - other.i);
    } //вычитание

    public Complex mul(Complex other) {//(a+bi)*(c+di) = (ac-bd) + (ad+bc)i
        return new Complex(
                this.r * other.r - this.i * other.i,
                this.r * other.i + this.i * other.r
                );
    }

    @Override
    public String toString() {
        return r + " + " + i + "i";
    }
}
