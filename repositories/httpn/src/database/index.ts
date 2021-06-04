import * as Sequelize from 'sequelize'
import { Options } from 'sequelize'
import models from './models'

import { configuration } from '../configuration'

const { testDatabase } = configuration

export const sequelize = new Sequelize(testDatabase)
models.forEach(model => model.define(sequelize, Sequelize))
models.forEach(model => model.associate(sequelize.models))
