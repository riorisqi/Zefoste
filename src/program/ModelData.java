package program;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author RIO RISQI
 */
public class ModelData
{
    private SimpleStringProperty nama;      // <String> nama lengkap pelanggan
    private SimpleIntegerProperty harga;    // <String> alamat pelanggan (bisa sesuai KTP)

    private SimpleStringProperty kategori;     // <String> pilihan mobil

    private SimpleStringProperty tglMulai;      // <String> tanggal mulai sewa
    private SimpleStringProperty desc;        // <Integer> biaya sewa

    //konstruktor
    public ModelData()
    {
        
    }
    
    public ModelData(String nama, int alamat,                                   //data pelanggan
                     String mobil,                                              //data mobil
                     String tglMulai, String biaya)                             //data sewa
    {
        this.nama = new SimpleStringProperty(nama);
        this.harga = new SimpleIntegerProperty(alamat);

        this.kategori = new SimpleStringProperty(mobil);

        this.tglMulai = new SimpleStringProperty(tglMulai);
        this.desc = new SimpleStringProperty(biaya);
    }

    //================================================================================================================================

    //method setter data pelanngan
    public void setNama(String nama)
    {
        this.nama.set(nama);
    }

    public void setHarga(int harga)
    {
        this.harga.set(harga);
    }

    //method getter data mobil
    public void setKategori(String kategori)
    {
        this.kategori.set(kategori);
    }

    //method setter data sewa
    public void setTglMulai(String tglMulai)
    {
        this.tglMulai.set(tglMulai);
    }
    
    public void setDesc(String desc)
    {
        this.desc.set(desc);
    }

    //================================================================================================================================

    //method getter data pelanggan
    public String getNama()
    {
        return nama.get();
    }

    public int getHarga()
    {
        return harga.get();
    }

    //method getter data mobil
    public String getKategori()
    {
        return kategori.get();
    }

    //method getter data sewa
    public String getTglMulai()
    {
        return tglMulai.get();
    }
    
    public String getDesc()
    {
        return desc.get();
    }
}
