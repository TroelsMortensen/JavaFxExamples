package okayreads.presentation.core;

import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.controllers.*;

public class ControllerConfigurator
{
    // private static DataManager dataManager;

    public static void configure(Object controller)
    {
        if(controller == null) return;
        switch(controller)
        {
            case AddAuthorController ctrl -> ctrl.init(getDataManager());
            case AddBookController ctrl -> ctrl.init(getDataManager());
            case AddShelfController ctrl -> ctrl.init(getDataManager());
            case SelectShelfController ctrl -> ctrl.init(getDataManager());
            case ViewShelfController ctrl -> ctrl.init(getDataManager());
            default -> throw new RuntimeException("Controller of type '" + controller.getClass().getSimpleName() + "' not valid.");
        }
    }

    private static DataManager getDataManager()
    {
        return new ListDataManager();
    }
}
