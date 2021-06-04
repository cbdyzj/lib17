export function define(sequelize, DataTypes) {
	return sequelize.define('User', {
		id: {
			type: DataTypes.INTEGER(10).UNSIGNED,
			allowNull: false,
			primaryKey: true,
			autoIncrement: true
		},
		name: {
			type: DataTypes.STRING(32),
			allowNull: false
		},
		password: {
			type: DataTypes.STRING(32),
			allowNull: false
		},
		age: {
			type: DataTypes.INTEGER(11),
			allowNull: false
		},
		createdAt: {
			type: DataTypes.DATE,
            field: 'created_at',
			allowNull: false,
			defaultValue: DataTypes.NOW
		},
		updatedAt: {
			type: DataTypes.DATE,
            field: 'updated_at',
			allowNull: false,
			defaultValue: DataTypes.NOW
		}
	}, {
			tableName: 'user'
		})
}

export function associate(models) { }
