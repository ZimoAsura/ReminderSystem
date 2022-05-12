import { Doctor } from './doctor';
import { Patient } from './patient';

export class Reminder {
    id: number;
    description: string;
    priority: string;
    status: string;
    patient: Patient;
    assign_time: Date;
    duration: Date;
}
