package rsreu.workcours.nbaprediction.actioncommand;


import rsreu.workcours.nbaprediction.loginlogout.command.ConnectedCommand;
import rsreu.workcours.nbaprediction.loginlogout.command.LoginCommand;
import rsreu.workcours.nbaprediction.loginlogout.command.LogoutCommand;
import rsreu.workcours.nbaprediction.moderator.command.*;
import rsreu.workcours.nbaprediction.user.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CONNECTED {
        {
            this.command = new ConnectedCommand();
        }
    },
    ADDUSER {
        {
            this.command = new AddUserCommand();
        }
    },
    MENUADDUSER {
        {
            this.command = new MenuAddUserCommand();
        }
    },
    EDITUSER {
        {
            this.command = new EditUserCommand();
        }
    },
    EDITADMINMODERATOR {
        {
            this.command = new EditAdminModeratorCommand();
        }
    },
    DELETEADMINMODERATOR {
        {
            this.command = new DeleteAdminModeratorCommand();
        }
    },
    SAVEADMINMODERATORDATA {
        {
            this.command = new SaveAdminModeratorDataCommand();
        }
    },
    ADDMATCH {
        {
            this.command = new AddMatchCommand();
        }
    },
    MENUADDMATCH {
        {
            this.command = new MenuAddMatchCommand();
        }
    },
    MENUUPDATEPOINTS {
        {
            this.command = new MenuUpdatePointsCommand();
        }
    },
    UPDATEPOINTS {
        {
            this.command = new UpdatePointsCommand();
        }
    },
    MENUPREDICTION {
        {
            this.command = new MenuPredictionCommand();
        }
    },
    ADDPREDICTION {
        {
            this.command = new AddPredictionCommand();
        }
    },
    MENURANTING {
        {
            this.command = new MenuRantingCommand();
        }
    },
    RANTING {
        {
            this.command = new RantingCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
