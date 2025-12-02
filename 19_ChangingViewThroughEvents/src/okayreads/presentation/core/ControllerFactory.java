package okayreads.presentation.core;

import javafx.util.Callback;
import okayreads.persistence.DataManager;
import okayreads.persistence.ListDataManager;
import okayreads.presentation.controllers.*;

public class ControllerFactory implements Callback<Class<?>, Object>
{
    @Override
    public Object call(Class<?> controllerType)
    {
        if(controllerType == AddAuthorController.class) return new AddAuthorController(getDataManager());
        if(controllerType == AddBookController.class) return new AddBookController(getDataManager());
        if(controllerType == AddShelfController.class) return new AddShelfController(getDataManager());
        if(controllerType == SelectShelfController.class) return new SelectShelfController(getDataManager());
        if(controllerType == ViewShelfController.class) return new ViewShelfController(getDataManager());
        // add more in the future

        throw new RuntimeException("Controller of type '" + controllerType.getSimpleName() + "' is not supported!");
    }

    private DataManager getDataManager()
    {
        return new ListDataManager();
    }
}
