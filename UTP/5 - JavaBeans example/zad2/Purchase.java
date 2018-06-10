/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.beans.*;

public class Purchase {

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    String prod;
    String data;
    Double price;

    public Purchase(String prod, String data, Double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public String getProd() {
        return prod;
    }

    public String getData() {
        return data;
    }

    public Double getPrice() {
        return price;
    }

    public synchronized void setProd(String prod) {
        this.prod = prod;
    }

    public synchronized void setData(String newData) {
        String oldData = this.data;
        this.data = newData;
        this.pcs.firePropertyChange("data", oldData, newData);
    }

    public synchronized void setPrice(Double newPrice) throws PropertyVetoException {
        Double oldPrice = this.price;
        this.vcs.fireVetoableChange("price", oldPrice, newPrice);
        this.price = newPrice;
        this.pcs.firePropertyChange("price", oldPrice, newPrice);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener(pcl);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.removePropertyChangeListener(pcl);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener vcl) {
        this.vcs.addVetoableChangeListener(vcl);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener vcl) {
        this.vcs.removeVetoableChangeListener(vcl);
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + this.getProd() + ", data=" + this.getData() + ", price=" + this.getPrice() + "]";
    }
}