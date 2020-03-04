export enum RoleType {
    ADMIN="ADMIN", USER="USER"
}

export class Role {
    roleId: number;
    role: RoleType;
}