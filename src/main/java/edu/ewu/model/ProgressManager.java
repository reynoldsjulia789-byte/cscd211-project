package edu.ewu.model;

import java.util.List;

public class ProgressManager
{
    public void saveAll(List<Storable> items, String directory)
    {
        int count;

        count = 0;

        for (Storable item: items)
        {
            item.saveProgress(directory + "/item" + count + ".dat");

            count++;
        }
    }

    public void backup(Storable item, String filename)
    {
        item.saveProgress(filename);
    }
}
