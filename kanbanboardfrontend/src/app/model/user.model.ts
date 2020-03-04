import { Role } from './role.model';
import { Task } from './task.model';

export class User {
    userId: number;
    firstname: string;
    lastname: string;
    email: string;
    roles: Role[];
    tasks: Task[];
    active: boolean;
}