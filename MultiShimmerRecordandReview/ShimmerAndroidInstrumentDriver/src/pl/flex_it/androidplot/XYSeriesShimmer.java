//Rev 0.2

package pl.flex_it.androidplot;

import com.androidplot.xy.XYSeries;

import java.util.List;


public class XYSeriesShimmer implements XYSeries {
    private List<Number> mDataY;
    private int mSeriesIndex;
    private String mTitle;
    private int mXAxisLimit = 500;
    private boolean mClearGraphatLimit = false;


    /**
     * Constructor
     *
     * @param datasource  the data source
     * @param seriesIndex series index for identification
     * @param title       series title for identification
     * @param xaxislimit  xaxis limit, see method setClearGraphatLimit
     */
    public XYSeriesShimmer(List<Number> datasource, int seriesIndex, String title, int xaxislimit) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = title;
        this.mXAxisLimit = xaxislimit;
    }

    /**
     * Constructor
     *
     * @param datasource  the data source
     * @param seriesIndex series index for identification
     * @param title       series title for identification
     */
    public XYSeriesShimmer(List<Number> datasource, int seriesIndex, String title) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = title;
    }

    /**
     * Constructor
     *
     * @param datasource  the data source
     * @param seriesIndex series index for identification
     */
    public XYSeriesShimmer(List<Number> datasource, int seriesIndex) {
        this.mDataY = datasource;
        this.mSeriesIndex = seriesIndex;
        this.mTitle = "";
    }

    /**
     * Constructor
     *
     * @param datasource the data source
     */
    public XYSeriesShimmer(List<Number> datasource) {
        this.mDataY = datasource;
        this.mTitle = "";
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public int size() {
        return mDataY.size();
    }

    @Override
    public Number getY(int index) {
        return mDataY.get(index);
    }

    @Override
    public Number getX(int index) {
        return index;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setClearGraphatLimit(boolean clear) {
        mClearGraphatLimit = clear;
    }

    public void setXAxisLimit(int limit) {
        mXAxisLimit = limit;
    }

    public void clearData() {
        mDataY.clear();
    }

    public void updateData(List<Number> datasource) {
        this.mDataY = datasource;
        if (mClearGraphatLimit) {
            if (mDataY.size() >= mXAxisLimit) {
                mDataY.clear();
            }

        } else {
            while (mDataY.size() >= mXAxisLimit) {
                mDataY.remove(0);
            }
        }
    }

    public void addData(Number data) {
        this.mDataY.add(data);
        if (mClearGraphatLimit) {
            if (mDataY.size() >= mXAxisLimit) {
                mDataY.clear();
            }
        } else {
            while (mDataY.size() >= mXAxisLimit) {
                mDataY.remove(0);
            }
        }

    }

}