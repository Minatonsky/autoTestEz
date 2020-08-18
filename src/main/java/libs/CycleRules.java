package libs;

public class CycleRules {
    int days;
    int cycleHours;
    int shiftHours;
    int driveHours;
    int restartHours;
    int shiftWorkHours;
    int shiftRestartHours;
    int breakHours;


    public static ECycleRules getCycleRules(int cycleId, int cargoType) {

            switch (cycleId) {
                case 0:
                    if(cargoType == 2) {return ECycleRules.USA_70hr_8days_Cargo2;}
                    else if (cargoType == 3) {return ECycleRules.USA_70hr_8days_Cargo3;}
                    else if (cargoType == 4) {return ECycleRules.USA_70hr_8days_Cargo4;}
                    else {return ECycleRules.USA_70hr_8days;}

                case 1:
                    if(cargoType == 2) {return ECycleRules.USA_60hr_7days_Cargo2;}
                    else if (cargoType == 3) {return ECycleRules.USA_60hr_7days_Cargo3;}
                    else if (cargoType == 4) {return ECycleRules.USA_60hr_7days_Cargo4;}
                    else {return ECycleRules.USA_60hr_7days;}
                case 2:
                    if (cargoType == 3) {return ECycleRules.Alaska_70hr_7days_Cargo3;}
                    else if (cargoType == 4) {return  ECycleRules.Alaska_70hr_7days_Cargo4;}
                    else {return ECycleRules.Alaska_70hr_7days;}
                case 3:
                    if (cargoType == 3) {return ECycleRules.Alaska_80hr_8days_Cargo3;}
                    else if (cargoType == 4) {return  ECycleRules.Alaska_80hr_8days_Cargo4;}
                    else {return ECycleRules.Alaska_80hr_8days;}
                case 4: return ECycleRules.Canada_70hr_7days;
                case 5: return ECycleRules.Canada_120hr_14days;
                case 6:
                    if(cargoType == 3) {return ECycleRules.Texas70hr_7days_Cargo3;}
                    else if (cargoType == 4) {return ECycleRules.Texas70hr_7days_Cargo4;}
                    else {return ECycleRules.Texas70hr_7days;}
                case 7:
                    if(cargoType == 2) {return ECycleRules.California_80hr_7days_Cargo2;}
                    else if (cargoType == 3) {return ECycleRules.California_80hr_7days_Cargo3;}
                    else if (cargoType == 4) {return ECycleRules.California_80hr_7days_Cargo4;}
                    else {return ECycleRules.California_80hr_7days;}

                default: return ECycleRules.CanadaNorth_60_80_7;
            }

    }
}
