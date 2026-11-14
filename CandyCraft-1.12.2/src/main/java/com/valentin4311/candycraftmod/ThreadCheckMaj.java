package com.valentin4311.candycraftmod;

/**
 * The 1.7.10 update checker polled a dead web page. It is disabled in the port; the class is kept so that
 * ClientTick.version etc. stay valid.
 * TODO(port): remove or reimplement with Forge's update-json checker.
 */
public class ThreadCheckMaj extends Thread {
   @Override
   public void run() {
      CandyCraft.shouldUpdate = false;
      ClientTick.threadFinished = true;
   }
}
