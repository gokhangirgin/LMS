package models;

public enum Phase {
	Discard,
	sortingForWashing, //0,1
	Washing,//0,2
	sortingForDrying,//0,3
	Drying,//0,4
	sortingForIroning,//0,5
	Ironing,//0,6
	sortingForPackaging,//0,7
	Packaging,//0,8
	OutOfFactory//0,9
}
