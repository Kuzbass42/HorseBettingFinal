package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by ZENIT on 29.05.2016.
 */
public class Tag extends TagSupport {
    private int pickHorse;
    private int winHorse;

    private double stake;
    private double odd;

    public void setPickHorse(int pickHorse) {
        this.pickHorse = pickHorse;
    }

    public void setWinHorse(int winHorse) {
        this.winHorse = winHorse;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (winHorse == pickHorse) {
                out.write(Double.toString(stake * odd));
            } else if (winHorse != 0) {
                out.write(Double.toString(0d));
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
