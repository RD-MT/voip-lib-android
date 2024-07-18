package org.libs.core;

public enum ConferenceLayout {
   Grid(0),
   ActiveSpeaker(1);

   protected final int mValue;

   private ConferenceLayout(int value) {
      this.mValue = value;
   }

   public static ConferenceLayout fromInt(int value) throws RuntimeException {
      switch(value) {
      case 0:
         return Grid;
      case 1:
         return ActiveSpeaker;
      default:
         throw new RuntimeException("Unhandled enum value " + value + " for ConferenceLayout");
      }
   }

   protected static ConferenceLayout[] fromIntArray(int[] values) throws RuntimeException {
      int arraySize = values.length;
      ConferenceLayout[] enumArray = new ConferenceLayout[arraySize];

      for(int i = 0; i < arraySize; ++i) {
         enumArray[i] = fromInt(values[i]);
      }

      return enumArray;
   }

   protected static int[] toIntArray(ConferenceLayout[] values) throws RuntimeException {
      int arraySize = values.length;
      int[] intArray = new int[arraySize];

      for(int i = 0; i < arraySize; ++i) {
         intArray[i] = values[i].toInt();
      }

      return intArray;
   }

   public int toInt() {
      return this.mValue;
   }
}
