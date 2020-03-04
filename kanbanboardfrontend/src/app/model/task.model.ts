export enum StatusType {
    TODO="TODO", INPROGRESS="INPROGRESS", WAITING="WAITING", DONE="DONE"
}

export class Task {
    id: number;
    title: string;
    content: string;
    creationTime: string;
    endDate: string;
    status: StatusType;
    completed: boolean;
}