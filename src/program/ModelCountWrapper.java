package program;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author RIO RISQI
 */
@XmlRootElement (name = "datalist")
public class ModelCountWrapper 
{
    private List<ModelCount> count;
    
    public void setCount(List<ModelCount> count)
    {
        this.count = count;
    }
    
    @XmlElement
    public List<ModelCount> getCount()
    {
        return count;
    }
}
