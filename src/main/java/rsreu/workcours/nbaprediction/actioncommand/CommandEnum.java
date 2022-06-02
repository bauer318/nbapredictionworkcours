package rsreu.workcours.nbaprediction.actioncommand;


import rsreu.workcours.nbaprediction.bettor.PredictionCommand;
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
    MENUEDITUSER {
        {
            this.command = new MenuEditUserCommand();
        }
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    EDITUSER {
        {
            this.command = new EditUserCommand();
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
    },
    UPDATEUSER{
        {
            this.command = new UpdateUserCommand();
        }
    },
    BLOCKUNBLOCKUSER{
        {
           this.command = new BlockUnblockUserCommand();
        }
    },
    EDITMATCH{
        {
            this.command = new EditMatchCommand();
        }
    },
    UPDATEMATCHDATA{
        {
          this.command = new UpdateMatchDataCommand();
        }
    },
    DELETEMATCH{
        {
            this.command = new DeleteMatchCommand();
        }
    },
    MENUBETTOR{
        {
            this.command = new PredictionCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
