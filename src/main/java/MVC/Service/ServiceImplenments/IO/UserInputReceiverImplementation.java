package MVC.Service.ServiceImplenments.IO;

import MVC.Service.InterfaceService.IO.UserInputReceiver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInputReceiverImplementation implements UserInputReceiver {
    @Override
    public BufferedReader getData() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
