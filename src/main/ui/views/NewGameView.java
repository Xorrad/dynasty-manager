package main.ui.views;

import main.core.Game;
import main.ui.controllers.BackController;
import main.ui.controllers.NewGameController;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;

public class NewGameView extends View {
    private JPanel formPanel;
    private JLabel titleLabel;
    private JLabel firstnameLabel;
    private JTextField firstnameField;
    private JLabel dynastyLabel;
    private JTextField dynastyField;
    private JButton startButton;
    private JButton backButton;

    public NewGameView(Game game) {
        super(game, "New Game Menu");
    }

    @Override
    public void init() {
        formPanel = new JPanel();

        titleLabel = new JLabel("Create your character.");

        firstnameLabel = new JLabel("Name");
        firstnameField = new JTextField("");

        dynastyLabel = new JLabel("Dynasty");
        dynastyField = new JTextField("");

        startButton = new JButton("Start");
        startButton.addActionListener(new NewGameController.StartController(game, this));

        backButton = new JButton("Back to main menu");
        backButton.addActionListener(new BackController(game));

        GroupLayout layout = new GroupLayout(formPanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(
                        layout.createParallelGroup(LEADING)
                            .addComponent(firstnameLabel)
                            .addComponent(dynastyLabel)
                            .addComponent(backButton))
                    .addGroup(
                        layout.createParallelGroup(LEADING)
                            .addComponent(firstnameField)
                            .addComponent(dynastyField)
                            .addComponent(startButton))
                )
            )
        );

        //layout.linkSize(SwingConstants.HORIZONTAL, btnUsername, btnGroup, btnAddress);

        layout.setVerticalGroup(layout
            .createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE).addComponent(titleLabel))
            .addGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(
                        layout.createParallelGroup(BASELINE)
                            .addComponent(firstnameLabel)
                            .addComponent(firstnameField))
                    .addGroup(
                        layout.createParallelGroup(BASELINE)
                            .addComponent(dynastyLabel)
                            .addComponent(dynastyField))
                    .addGroup(
                        layout.createParallelGroup(BASELINE)
                            .addComponent(backButton)
                            .addComponent(startButton))
                )
            )
        );

        formPanel.setLayout(layout);

        BoxLayout layoutMain = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutMain);
        this.add(formPanel);
    }

    public JTextField getFirstnameField() {
        return firstnameField;
    }

    public JTextField getDynastyField() {
        return dynastyField;
    }
}
