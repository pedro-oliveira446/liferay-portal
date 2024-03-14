interface Notification {
	notificationDescription: string;
	notificationName: string;
	notificationTypeEmail: boolean;
	notificationTypeUser: boolean;
	recipientType: string;
	recipientTypeData: RoleRecipientType | ScriptRecipientType;
	template: string;
	templateLanguage: string;
}

interface RoleRecipientType {
	roleName: string;
}

interface ScriptRecipientType {
	script: string;
	scriptLanguage: string;
}
