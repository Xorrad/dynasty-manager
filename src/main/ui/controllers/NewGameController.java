package main.ui.controllers;

import main.core.Game;
import main.core.state.IngameState;
import main.core.world.World;
import main.core.world.character.Character;
import main.core.world.dynasty.Dynasty;
import main.core.world.dynasty.House;
import main.core.world.trait.Traits;
import main.ui.views.menu.NewGameView;
import main.util.Location;

import java.awt.event.ActionEvent;

public class NewGameController {
    public static class StartController extends Controller {
        private NewGameView view;

        public StartController(Game game, NewGameView view) {
            super(game);
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            World world = new World(this.game);

            // Extract information from textfields and other inputs.
            String characterName = this.view.getFirstnameField().getText();
            String dynastyName = this.view.getDynastyField().getText();

            // Create the player dynasty and house.
            Dynasty playerDynasty = new Dynasty.Builder(world).name(dynastyName).get();
            House playerHouse = new House.Builder(world, playerDynasty).name(dynastyName).get();

            // Create the player character.
            Character playerCharacter = new Character.Builder(world)
                    .name(characterName)
                    .house(playerHouse)
                    .location(new Location(0, 0))
                    .trait(Traits.ATHLETIC)
                    .get();

            this.game.setState(new IngameState(this.game, world, playerCharacter.getId()));
            //this.game.getState().openView(new MainGameView(this.game), true);
        }
    }
}
