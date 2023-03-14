public class Toy implements Comparable<Toy>{
    private int id;   // id
    private String name; // название игрушки
    private int count; // количество на складе магазина
    private int dropt; // Частота выпадения

    public Toy(int id, String name, int count, int dropt){
        this.id = id;
        this.name = name;
        this.count = count;
        this.dropt = dropt;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
    public int getDropt(){
        return dropt;
    }
    
    public void setDropt(int dropt){
        this.dropt = dropt;
    }
    @Override
    public int compareTo(Toy o) {
        return o.dropt - this.dropt;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Toy other = (Toy) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}