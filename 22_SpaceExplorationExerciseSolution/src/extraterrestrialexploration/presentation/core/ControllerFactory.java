package extraterrestrialexploration.presentation.core;

import extraterrestrialexploration.persistence.DataManager;
import extraterrestrialexploration.persistence.FileDataManager;
import extraterrestrialexploration.presentation.controllers.MainViewController;
import extraterrestrialexploration.presentation.controllers.alienmanagement.AddAlienController;
import extraterrestrialexploration.presentation.controllers.alienmanagement.ListAliensController;
import extraterrestrialexploration.presentation.controllers.alienmanagement.ShowAlienController;
import extraterrestrialexploration.presentation.controllers.alienmanagement.UpdateAlienController;
import extraterrestrialexploration.presentation.controllers.encountermanagement.AddEncounterController;
import extraterrestrialexploration.presentation.controllers.encountermanagement.ListEncountersController;
import extraterrestrialexploration.presentation.controllers.encountermanagement.ShowEncounterController;
import extraterrestrialexploration.presentation.controllers.encountermanagement.UpdateEncounterController;
import extraterrestrialexploration.presentation.controllers.explorermanagement.AddExplorerController;
import extraterrestrialexploration.presentation.controllers.explorermanagement.ListExplorersController;
import extraterrestrialexploration.presentation.controllers.explorermanagement.MoveExplorerController;
import extraterrestrialexploration.presentation.controllers.explorermanagement.ShowExplorerController;
import extraterrestrialexploration.presentation.controllers.explorermanagement.UpdateExplorerController;
import extraterrestrialexploration.presentation.controllers.planetmanagement.AddPlanetController;
import extraterrestrialexploration.presentation.controllers.planetmanagement.ListPlanetsController;
import extraterrestrialexploration.presentation.controllers.planetmanagement.ShowPlanetController;
import extraterrestrialexploration.presentation.controllers.planetmanagement.UpdatePlanetController;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object>
{
    @Override
    public Object call(Class<?> controllerType)
    {
        if (controllerType == MainViewController.class) return new MainViewController();

        if (controllerType == ListPlanetsController.class) return new ListPlanetsController(getDataManager());
        if (controllerType == AddPlanetController.class) return new AddPlanetController(getDataManager());
        if (controllerType == ShowPlanetController.class) return new ShowPlanetController(getDataManager());
        if (controllerType == UpdatePlanetController.class) return new UpdatePlanetController(getDataManager());

        if (controllerType == ListExplorersController.class) return new ListExplorersController(getDataManager());
        if (controllerType == AddExplorerController.class) return new AddExplorerController(getDataManager());
        if (controllerType == ShowExplorerController.class) return new ShowExplorerController(getDataManager());
        if (controllerType == UpdateExplorerController.class) return new UpdateExplorerController(getDataManager());
        if (controllerType == MoveExplorerController.class) return new MoveExplorerController(getDataManager());

        if (controllerType == ListAliensController.class) return new ListAliensController(getDataManager());
        if (controllerType == AddAlienController.class) return new AddAlienController(getDataManager());
        if (controllerType == ShowAlienController.class) return new ShowAlienController(getDataManager());
        if (controllerType == UpdateAlienController.class) return new UpdateAlienController(getDataManager());

        if (controllerType == ListEncountersController.class) return new ListEncountersController(getDataManager());
        if (controllerType == AddEncounterController.class) return new AddEncounterController(getDataManager());
        if (controllerType == ShowEncounterController.class) return new ShowEncounterController(getDataManager());
        if (controllerType == UpdateEncounterController.class) return new UpdateEncounterController(getDataManager());

        throw new RuntimeException("Controller of type '" + controllerType.getSimpleName() + "' is not supported!");
    }

    private DataManager getDataManager()
    {
        return new FileDataManager();
    }
}
