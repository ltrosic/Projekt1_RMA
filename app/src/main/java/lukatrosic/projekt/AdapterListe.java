package lukatrosic.projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {
    //Nista se ne mijenja ode osim imena liste
    private List<Crypto> cryptos;
    private LayoutInflater layoutInflater;
    private ItemClickInterface itemClickInterface;

    public AdapterListe(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public void setCrypto(List<Crypto> cryptos) {
        this.cryptos = cryptos;
    }

    @NonNull
    @Override
    public Red onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.red_liste,parent,false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Red holder, int position) {
        Crypto crypto = cryptos.get(position);
        holder.symbol.setText(crypto.getSymbol());
    }

    @Override
    public int getItemCount() {
        /*
        if (osobe==null){
            return 0;
        }else{
            return  osobe.size();
        }
        */
        return cryptos == null ? 0 : cryptos.size();
    }

    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView symbol;
        private TextView highValue;

        public Red(@NonNull View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symbol);
            highValue = itemView.findViewById(R.id.highValue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickInterface==null){
                return;
            }
            itemClickInterface.onItemClick(cryptos.get(getAdapterPosition()));
        }
    }

    public interface ItemClickInterface{
        void onItemClick(Crypto crypto);
    }

    public void setItemClickInterface(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }
}
