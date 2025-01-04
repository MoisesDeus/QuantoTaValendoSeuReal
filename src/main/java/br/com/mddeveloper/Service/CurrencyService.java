package br.com.mddeveloper.Service;

import br.com.mddeveloper.DTO.CurrencyDTO;
import br.com.mddeveloper.Model.Currency;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrencyService {

    public CurrencyDTO formatCurrency(Currency currency) {
        String formattedName = currency.getName().replace("/", " para ");

        String formattedValue = String.format("%.2f BRL", currency.getValue());

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy ás HH:mm").format(new Date());

        return new CurrencyDTO(formattedName, formattedValue, formattedDate);
    }
}
