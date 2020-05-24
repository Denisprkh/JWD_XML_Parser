package by.prokhorenko.xmlparser.builder;

import by.prokhorenko.xmlparser.entity.*;
import by.prokhorenko.xmlparser.exception.TouristVoucherParserException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTouristVoucherBuilder {
    protected Set<TouristVoucher> touristVouchers;

    public AbstractTouristVoucherBuilder(){
        this.touristVouchers = new HashSet<>();
    }

    public AbstractTouristVoucherBuilder(Set<TouristVoucher> touristVouchers){
        this.touristVouchers = touristVouchers;
    }

    public Set<TouristVoucher> getTouristVouchers(){
        return touristVouchers;
    }
    public abstract void buildTouristVouchers(String fileLink) throws TouristVoucherParserException;
}
