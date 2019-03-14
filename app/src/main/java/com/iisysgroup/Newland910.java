package com.iisysgroup;

import com.iisysgroup.poslib.commons.emv.EmvTransactionType;
import com.iisysgroup.poslib.deviceinterface.Device;
import com.iisysgroup.poslib.deviceinterface.EmvCardReader;
import com.iisysgroup.poslib.deviceinterface.OcrScanner;
import com.iisysgroup.poslib.deviceinterface.Printer;
import com.iisysgroup.poslib.deviceinterface.printer.Printable;
import com.iisysgroup.poslib.deviceinterface.printer.PrinterState;

import java.util.List;

/**
 * Created by Itex-PC on 03/09/2018.
 */

public class Newland910 implements Device {
    @Override
    public void startEmvTransaction(long l, long l1, EmvTransactionType emvTransactionType, EmvCallback emvCallback) {

    }

    @Override
    public void executeEmvOnlineResponse(String s, String s1, String s2, String s3) {

    }

    @Override
    public void startScan(boolean b, boolean b1, ScannerCallback scannerCallback) {

    }

    @Override
    public void stopScan() {

    }

    @Override
    public void print(List<Printable> list, PrinterCallback printerCallback) {

    }

    @Override
    public PrinterState getPrinterStatus() {
        return null;
    }
}
