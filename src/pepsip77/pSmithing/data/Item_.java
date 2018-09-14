package pepsip77.pSmithing.data;

public enum Item_ {
    DAGGER(0, 1119, new int[] {1205, 1203, 1207, 1209, 1211, 1213}),
    SWORD(1, 1119, new int[] {1277, 1279, 1281, 1285, 1287, 1289}),
    SCIMITAR(2, 1119, new int[] {1321, 1323, 1325, 1329, 1331, 1333}),
    LONG_SWORD(3, 1119, new int[] {1291, 1293, 1295, 1299, 1301, 1303}),
    TWO_H_SWORD(4, 1119, new int[] {1307, 1309, 1311, 1315, 1317, 1319}),
    AXE(0, 1120, new int[] {1351, 1349, 1353, 1355, 1357, 1359}),
    MACE(1, 1120, new int[] {1422, 1420, 1424, 1428, 1430, 1432}),
    WARHAMMER(2, 1120, new int[] {1337, 1335, 1339, 1343, 1345, 1347}),
    BATTLE_AXE(3, 1120, new int[] {1375, 1363, 1365, 1369, 1371, 1373}),
    CHAIN_BODY(0, 1121, new int[] {1103, 1101, 1105, 1109, 1111, 1113}),
    PLATE_LEGS(1, 1121, new int[] {1075, 1067, 1069, 1071, 1073, 1079}),
    PLATE_SKIRT(2, 1121, new int[] {1087, 1081, 1083, 1085, 1091, 1093}),
    PLATE_BODY(3, 1121, new int[] {1117, 1115, 1119, 1121, 1123, 1127}),
    MEDIUM_HELM(0, 1122, new int[] {1139, 1137, 1141, 1143, 1145, 1147}),
    FULL_HELM(1, 1122, new int[] {1155, 1153, 1157, 1159, 1161, 1163}),
    SQUARE_SHIELD(2, 1122, new int[] {1173, 1175, 1177, 1181, 1183, 1185}),
    KITE_SHIELD(3, 1122, new int[] {1189, 1191, 1193, 1197, 1199, 1201}),
    NAILS(4, 1122, new int[] {4819, 4820, 1539, 4822, 4823, 4824}),
    BOLTS(0, 1123, new int[] {9375, 9377, 9378, 9379, 9380, 9381}),
    ARROWTIPS(1, 1123, new int[] {39, 40, 41, 42, 43, 44}),
    THROWING_KNIVES(2, 1123, new int[] {864, 863, 865, 866, 867, 868});

    private int action2Id;
    private int action3Id;
    private int[] itemIds;

    Item_(int action2Id, int action3Id, int[] itemIds){
        this.action2Id = action2Id;
        this.action3Id = action3Id;
        this.itemIds = itemIds;
    }

    public int getAction2Id() {
        return action2Id;
    }

    public int getAction3Id() {
        return action3Id;
    }

    public int getItemId() {
        return itemIds[Data.currentBar.getId()];
    }
}
