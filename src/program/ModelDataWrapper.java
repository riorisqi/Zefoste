package program;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RIO RISQI
 */
@XmlRootElement (name = "datalist")
public class ModelDataWrapper 
{
    private List<ModelData> data;
    
    public void setDatas(List<ModelData> data)
    {
        this.data = data;
    }
    
    @XmlElement
    public List<ModelData> getDatas()
    {
        return data;
    }
}
