export function define(sequelize, DataTypes) {
    return sequelize.define('Foo', {
        id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            primaryKey: true,
            autoIncrement: true,
        },
        name: {
            type: DataTypes.STRING,
            defaultValue: '',
            allowNull: false,
        },
        content: {
            type: DataTypes.STRING,
            defaultValue: '',
            allowNull: false,
        },
        createdAt: {
            type: DataTypes.DATE,
            field: 'created_at',
			defaultValue: DataTypes.NOW
        },
        updatedAt: {
            type: DataTypes.DATE,
            field: 'updated_at',
			defaultValue: DataTypes.NOW
            
        },
    }, {
            tableName: 'foo',
        })
}

export function associate(models) {
    models.Foo.hasMany(models.Bar, {
        sourceKey: 'name',
        foreignKey: 'name',
        constraints: false,
    })
}