package org.libs.mediastream;

public class MediastreamException extends Exception {
   public MediastreamException() {
   }

   public MediastreamException(String detailMessage) {
      super(detailMessage);
   }

   public MediastreamException(Throwable e) {
      super(e);
   }

   public MediastreamException(String detailMessage, Throwable e) {
      super(detailMessage, e);
   }
}
