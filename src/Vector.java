import java.util.Arrays;

public class Vector {
    double[] components;
    String lang;

    public Vector(double[] components, String lang) {
        double length = 0;
        for (double component : components) length += Math.pow(component, 2);

        length = Math.sqrt(length);

        for (int i = 0; i < components.length; i++)
            components[i] /= length;

        this.components = components;
        this.lang = lang;
    }

    public double[] getComponents() {
        return components;
    }

    public String getLang() {
        return lang;
    }

    public void setComponents(double[] components) {
        this.components = components;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "lang='" + lang + '\'' +
                ", components=" + Arrays.toString(components) +
                '}';
    }
}
